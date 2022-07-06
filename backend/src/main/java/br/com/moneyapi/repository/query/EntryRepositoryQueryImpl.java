package br.com.moneyapi.repository.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import br.com.moneyapi.model.Entry;
import br.com.moneyapi.model.Entry_;
import br.com.moneyapi.repository.filter.EntryFilter;

public class EntryRepositoryQueryImpl implements EntryRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Entry> filter(EntryFilter entryFilter, Pageable pageable) {
       
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Entry> criteria = builder.createQuery(Entry.class);
        
        Root<Entry> root = criteria.from(Entry.class);
        
        Predicate[] predicates = createRestrictions(entryFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Entry> query = manager.createQuery(criteria);
        addRestrictionsToQuery(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(entryFilter));
    }

    private Predicate[] createRestrictions(EntryFilter entryFilter, CriteriaBuilder builder, Root<Entry> root) {
        
        List<Predicate> predicates = new ArrayList<>();

        if(!ObjectUtils.isEmpty(entryFilter.getDescription())) {
			predicates.add(
                builder.like(
					builder.lower(root.get(Entry_.description)), "%" + entryFilter.getDescription().toLowerCase() + "%"));
		}

		if (entryFilter.getDueDateFrom() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Entry_.dueDate), entryFilter.getDueDateFrom()));
		}

		if (entryFilter.getDueDateUntil() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Entry_.dueDate), entryFilter.getDueDateUntil()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void addRestrictionsToQuery(TypedQuery<Entry> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecords = pageable.getPageSize();
        int firstRecord = currentPage * totalRecords;
        query.setFirstResult(firstRecord);
        query.setMaxResults(totalRecords);
    }
    
    private Long total(EntryFilter entryFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        
        Root<Entry> root = criteria.from(Entry.class);

        Predicate[] predicates = createRestrictions(entryFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
