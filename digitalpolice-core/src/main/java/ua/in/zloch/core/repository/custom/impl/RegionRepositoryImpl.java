package ua.in.zloch.core.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.in.zloch.core.entity.Region;
import ua.in.zloch.core.repository.custom.RegionRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RegionRepositoryImpl implements RegionRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public List<Region> search(List<Long> regionIds) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Region> query = builder.createQuery(Region.class);
        Root<Region> region = query.from(Region.class);

        if (regionIds != null)
            query.where(region.get("koatuu").in(regionIds));

        return em.createQuery(query).getResultList();
    }
}
