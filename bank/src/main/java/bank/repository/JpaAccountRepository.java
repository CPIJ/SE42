package bank.repository;

import bank.domain.Account;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class JpaAccountRepository implements AccountRepository {

    private final EntityManager entityManager;

    public JpaAccountRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Account account) {
        entityManager.persist(account);
    }

    public void edit(Account account) {
        entityManager.merge(account);
    }

    public void remove(Account account) {
        entityManager.remove(entityManager.merge(account));
    }

    public Account find(Long id) {
        return entityManager.find(Account.class, id);
    }

    public Account findByAccountNr(Long accountNr) {
        Query query = entityManager.createNamedQuery("Account.findByAccountNr", Account.class);
        query.setParameter("accountNr", accountNr);
        return (Account) query.getSingleResult();
    }

    public List<Account> findAll() {
        CriteriaQuery criteriaQuery = entityManager.getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(Account.class));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public int count() {
        Query q = entityManager.createNamedQuery("Account.count", Account.class);
        return ((Long) q.getSingleResult()).intValue();
    }
}
