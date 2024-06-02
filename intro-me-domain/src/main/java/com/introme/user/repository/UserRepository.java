package com.introme.user.repository;


import com.introme.user.UserProviderType;
import com.introme.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.introme.user.entity.QUser.user;


public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}


interface UserRepositoryCustom {
    User findByEmail(String email);

    User findByEmailAndProviderType(String email, UserProviderType providerType);
}

class UserRepositoryCustomImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {
    public UserRepositoryCustomImpl(Class<User> domainClass) {
        super(domainClass);
    }


    @Override
    public User findByEmail(String email) {
        return from(user)
                .where(user.email.eq(email))
                .fetchFirst();
    }

    @Override
    public User findByEmailAndProviderType(String email, UserProviderType providerType) {
        return from(user)
                .where(user.email.eq(email).and(user.key.type.eq(providerType)))
                .fetchFirst();
    }
}
