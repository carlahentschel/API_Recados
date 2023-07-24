package com.recados.ApiMuralRecados.repositories.specifications;

import com.recados.ApiMuralRecados.models.UserTask;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class UserTaskSpecification {
    public static Specification<UserTask> filterByTitleAndFinishedAndFavorite(String title, boolean finished, boolean favorite) {
        return (root, query, criteriaBuilder) -> {
            var conditions = new ArrayList<Predicate>();

            if(title != null && !title.isEmpty()) {
                conditions.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%")
                );
            }

//            if(finished != null) {
//
//            }
//
//            if(favorite != null) {
//
//            }
            return criteriaBuilder.and(conditions.toArray(new Predicate[0]));
        };
    }
}
