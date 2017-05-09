package bank.service;

import bank.domain.Account;
import bank.repository.AccountRepository;
import bank.repository.JpaAccountRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AccountService {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");

    public Account createAccount(Long accountNr) {
        EntityManager em = emf.createEntityManager();
        AccountRepository accountRepository = new JpaAccountRepository(em);
        Account account = new Account(accountNr);
        em.getTransaction().begin();
        try {
            accountRepository.create(account);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return account;
    }

    public Account getAccount(Long accountNr) {
        EntityManager em = emf.createEntityManager();
        AccountRepository accountRepository = new JpaAccountRepository(em);
        Account account = null;
        em.getTransaction().begin();
        try {
            account = accountRepository.findByAccountNr(accountNr);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return account;
    }
}
