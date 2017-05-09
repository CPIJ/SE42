package bank.domain;

import bank.repository.AccountRepository;
import bank.repository.JpaAccountRepository;
import bank.util.DatabaseCleaner;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class week11 {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bankPU");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Before
    public void clearDb() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("bankPU").createEntityManager();
        DatabaseCleaner databaseCleaner = new DatabaseCleaner(entityManager);

        try {
            databaseCleaner.clean();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test // Hoe werken persist en commit in samenhang met de database.
    public void question1() {
        Account account = new Account(111L);

        entityManager.getTransaction().begin();
        // De transactie wordt geopend, klaar om te beginnen.

        entityManager.persist(account);
        // Het account wordt klaar gezet om in de database te gaan.

        assertNull(account.getId());
        // Het account zit nog niet in de database, er is dus nog geen ID gegevereerd.

        entityManager.getTransaction().commit();
        // INSERT INTO statement wordt uitgevoerd.

        System.out.println("AccountId: " + account.getId());
        // Het account bestaat nu in de database, er is dus ook een ID opgeslagen.

        assertTrue(account.getId() > 0L);
        //
    }

    @Test
    public void question2() {
        // In deze test gebeurd ongeveer hetzelfde als in de vorige test
        // Er wordt een accout aangemaakt en deze wordt klaargezet om op te slaan in
        // de database.
        // Alleen nu wordt er een error gesimuleerd door rollback aan te roepen.

        Account account = new Account(111L);

        entityManager.getTransaction().begin();

        entityManager.persist(account);

        assertNull(account.getId());

        // Hier worden de aanpassingen terug gezet. Er worden GEEN statements uitgevoegd.
        entityManager.getTransaction().rollback();

        AccountRepository accountRepository = new JpaAccountRepository(entityManager);

        assertTrue(accountRepository.findAll().isEmpty());
        // Find all voert een custom query uit, deze is te vinden boven aan de klasse.
    }

    @Test // Flushen maar
    public void question3() {
        Long expected = -100L;

        Account account = new Account(111L);
        account.setId(expected);

        entityManager.getTransaction().begin();

        entityManager.persist(account);

        // assertNotEquals(expected, account.getId());
        // -> deze test faalt, er is nog niks gecommit dus het account heeft nog steeds
        //    een id van -100. Pas als er gecommit wordt wordt het ID vastgesteld.

        entityManager.flush();
        // INSERT Statement wordt uitgevoerd.

        // Flush wordt gebruikt om de veranderde data binnen een transactie GELIJK op te slaan
        // deze methode wacht dus niet op het commit() commando.

        // assertEquals(expected, account.getId());
        // -> Deze test faalt ook, het accountId is namelijk niet -100.
        //    Dit komt omdat het ID gegenereerd wordt door JPA en deze houdt geen rekening met
        //    eigen inbreng.

        entityManager.getTransaction().commit();
    }

    @Test // Veranderingen na de persist
    public void question4() {
        Long expectedBalance = 400L;

        Account account = new Account(114L);

        entityManager.getTransaction().begin();

        entityManager.persist(account);

        account.setBalance(expectedBalance);

        entityManager.getTransaction().commit();
        // INSERT statement wordt uitgevoerd.

        // De balance wordt gezet in regel 123, daarom is deze gelijk aan de expected.
        assertEquals(expectedBalance, account.getBalance());

        Long previousId = account.getId();

        account = null;

        EntityManager em2 = entityManagerFactory.createEntityManager();

        em2.getTransaction().begin();

        Account found = em2.find(Account.class, previousId);

        // Er wordt via een nieuwe entityManager gezocht naar een Account met hetzelfde Id als de eerdere.
        // Deze had de expectedBalance opgeslagen, daarom is hij hier hetzelfde.
        assertEquals(expectedBalance, found.getBalance());
    }

    @Test
    public void question5() {
        // Gedupliceerde code
        Long expectedBalance = 400L;
        Account originalAccount = new Account(114L);
        entityManager.getTransaction().begin();
        entityManager.persist(originalAccount);
        originalAccount.setBalance(expectedBalance);
        entityManager.getTransaction().commit();
        assertEquals(expectedBalance, originalAccount.getBalance());
        Long previousId = originalAccount.getId();
        EntityManager em2 = entityManagerFactory.createEntityManager();
        em2.getTransaction().begin();
        Account foundAccount = em2.find(Account.class, previousId);
        assertEquals(expectedBalance, foundAccount.getBalance());

        Long newBalance = 200L;

        originalAccount.setBalance(newBalance);

        entityManager.merge(originalAccount);

        em2.refresh(foundAccount);

        // Deze twee waarden zijn niet hetzelfde aangezien ze door andere entityManagers bijgehouden worden.
        assertNotEquals(newBalance, foundAccount.getBalance());
    }

    // Merge is een van de lastigere methoden uit JPA api. Het is belangrijk dat je deze opgave
    // daarom zorgvuldig uitvoert.
    @Test
    public void question6_scenario1() {
        Account acc = new Account(1L);

        Long balance = 100L;

        entityManager.getTransaction().begin();

        entityManager.persist(acc);

        acc.setBalance(balance);

        entityManager.getTransaction().commit();

        assertEquals(balance, acc.getBalance());

        Account accFound = entityManager.find(Account.class, acc.getId());

        assertEquals(balance, accFound.getBalance());
        // voeg asserties toe om je verwachte waarde van de attributen te verifieren.
        // doe dit zowel voor de bovenstaande java objecten als voor opnieuw bij de
        // entitymanager opgevraagde objecten met overeenkomstig Id.
    }

    @Test
    public void question6_scenario2() {
        Long balance2a = 211L;

        Account account = new Account(2L);
        Account acc9 = new Account(9L);

        entityManager.getTransaction().begin();

        acc9 = entityManager.merge(account);

        account.setBalance(balance2a);
        acc9.setBalance(balance2a * 2);

        entityManager.getTransaction().commit();

        AccountRepository accountRepository = new JpaAccountRepository(entityManager);

        Account found = accountRepository.findByAccountNr(2L);

        // Account heeft acc9 overschreven door de merge. Het account nummer wordt dan ook 2.
        // De balans is niet hetzelfde omdat acc9 als laatste het bedrag overschrijft. dit wordt dan ook opgeslagen.
        assertEquals(acc9.getId(), found.getId());
        assertNotEquals(acc9.getBalance(), account.getBalance());
    }

    @Test
    public void question6_scenario3() {
        Long balance3b = 322L;
        Long balance3c = 333L;

        Account acc = new Account(3L);

        entityManager.getTransaction().begin();

        Account acc2 = entityManager.merge(acc);

        assertFalse(entityManager.contains(acc));
        // Dit is false omdat 'acc' nooit toegevoegd is (persist).

        assertTrue(entityManager.contains(acc2));
        // Dit is true omdat dit object op het moment zich bevint in de entitymanager, wachtende om gecommit te worden.

        assertNotEquals(acc, acc2);
        // Deze objecten zijn nog steeds niet hetzelfde voor Java, de geheugenlocaties zijn anders.

        acc2.setBalance(balance3b);
        acc.setBalance(balance3c);

        entityManager.getTransaction().commit();

        // Account accFound = entityManager.find(Account.class, acc.getId());
        // Dit faalt, de id voor acc is nooit gezet aangezien deze nooit is toegevoegd aan de transactie.

        Account acc2Found = entityManager.find(Account.class, acc2.getId());
        assertEquals(balance3b, acc2Found.getBalance());
        // Dit slaagt, we hebben deze waarde meegegeven voordat er gecommit wordt.
    }

    @Test
    public void question6_scenario4() {
        Account account = new Account(114L);
        account.setBalance(450L);

        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();

        Account account2 = new Account(11L);

        Account tweedeAccountObject = account2;
        tweedeAccountObject.setBalance(650L);

        assertEquals((Long) 650L, account2.getBalance());
        // account2 en tweedeAccountObject linken naar hetzelfde geheugenaddres. Vandaar dat een verandering in
        // tweedeObjectAccount account2 aantast.

        account2.setId(account.getId());

        entityManager.getTransaction().begin();

        account2 = entityManager.merge(account2);

        assertSame(account, account2);
        // Het ID van account wordt gezet in account2. Dit zorgt ervoor dat, na een merge, JPA deze twee objecten als
        // Hetzelfde ziet.

        assertTrue(entityManager.contains(account2));
        // Wederom, JPA bepaald een object door het ID. het id van account2 bestaat binnen de manager omdat het gezet
        // word vanaf account.

        assertFalse(entityManager.contains(tweedeAccountObject));
        // account2 heeft hetzelfde geheugenaddres als tweedeAccountObject, als account2 dan overschreven wordt door
        // account wordt tweedeAccountObject ook overschreven.

        tweedeAccountObject.setBalance(850L);

        assertEquals((Long) 650L, account.getBalance());
        // Alhoewel de twee objecten dezelfde ID hebben, en dus gelijk zijn voor JPA, heeft account nog steeds een
        // ander geheugenadres als tweedeAccountObject.

        System.out.println("account: " + "ID = " + account.getId() + " Hash = " + account.hashCode() + '\n' +
                "account2: " + "ID = " + account2.getId() + " Hash = " + account2.hashCode() + '\n' +
                "tweedeAccountObject: " + "ID = " + tweedeAccountObject.getId() + " Hash = " + tweedeAccountObject.hashCode() + '\n');
    }

    @Test // Find en Clear
    public void question7() {
        Account acc1 = new Account(77L);

        entityManager.getTransaction().begin();
        entityManager.persist(acc1);
        entityManager.getTransaction().commit();
        //Database bevat nu een account.

        // scenario 1
        Account accF1;
        Account accF2;
        accF1 = entityManager.find(Account.class, acc1.getId());
        accF2 = entityManager.find(Account.class, acc1.getId());
        assertSame(accF1, accF2);
        // scenario 2
        accF1 = entityManager.find(Account.class, acc1.getId());
        entityManager.clear();
        accF2 = entityManager.find(Account.class, acc1.getId());
        assertNotSame(accF1, accF2);

        // Bij scenario 1 acc1 nog net opgeslagen. Deze bevindt zich dus in de cache.
        // Als accF1 en F2 dan geinstantieerd worden komen ze uit de cache en hebben ze hetzelfde geheugen adres.

        // Bij scenrario 2 is de cache gewist, acc1 zit daar dus niet meer in.
        // Als accF2 en F2 dan geinstantieerd worden voert JPA nieuwe queries uit en worden er nieuwe objecten gemaakt.
        // Deze hebben dan ook een ander geheugenadres.
    }

    @Test
    public void question8() {
        Account acc1 = new Account(88L);

        entityManager.getTransaction().begin();
        entityManager.persist(acc1);
        entityManager.getTransaction().commit();

        Long id = acc1.getId();
        //Database bevat nu een account.

        entityManager.remove(acc1);

        assertEquals(id, acc1.getId());
        // Alhoewel acc1 verwijderd is uit de database bestaat hij nog wel in het geheugen van de applicatie.

        Account accFound = entityManager.find(Account.class, id);

        assertNull(accFound);
        // accFound is null omdat er geprobeerd wordt een object op te halen uit de db die niet bestaat.
    }

    @Test
    public void question9() {
        // Tussen SEQUENCE en IDENTITY zat bij mij geen verschil.

        // TABLE maakte wel verschil, hier faalde een aantal tests (1,2 en 9) dit gebeurd omdat als deze
        // modus een id wordt gezet er een query naar de db gedaan naar wat de laatste id is, er wordt dus altijd
        // een id gezet.
    }
}