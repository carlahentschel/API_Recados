package com.recados.ApiMuralRecados.repositories.specifications;

import com.recados.ApiMuralRecados.models.UserTask;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.UUID;

public class UserTaskSpecification {
    public static Specification<UserTask> filterByTitleAndFinishedAndFavorite(UUID userId, String title, Boolean finished, Boolean favorite) {
        return (root, query, criteriaBuilder) -> {
            var conditions = new ArrayList<Predicate>();

            conditions.add(criteriaBuilder.equal(root.get("idUser"), userId));

            if(title != null && !title.isEmpty()) {
                conditions.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%")
                );
            }

            if(finished != null) {
                conditions.add(criteriaBuilder.equal(root.get("finished"), finished));
            }

            if(favorite != null) {
                conditions.add(criteriaBuilder.equal(root.get("favorite"), favorite));
            }
            return criteriaBuilder.and(conditions.toArray(new Predicate[0]));
        };
    }
}
