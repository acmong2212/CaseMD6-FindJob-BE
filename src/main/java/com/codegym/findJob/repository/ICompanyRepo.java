package com.codegym.findJob.repository;

import com.codegym.findJob.model.Company;
import com.codegym.findJob.model.CompanyShorted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICompanyRepo extends JpaRepository<Company, Long> {
    List<Company> findUsersByIdIsNotLike(Long id);

    Optional<Company> findByEmail(String email);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    Company findByEmailAndPassword(String email, String password);

    @Query(nativeQuery = true, value = "select count(id) from company")
    int countUsers();

    List<Company> findAllByEmailContaining(String email);
    @Query(nativeQuery = true, value = "select count(post.id) as countid,post.company_id from post where post.status = true  group by post.company_id order by countid desc limit 5")
    List<CompanyShorted> findListCompanyShorted();

    //Thao
    //ham hien thi so luong apply bai post cua cong ty
    @Query(nativeQuery = true, value = "select users_id from post join apply on post.id = apply.post_id where post.id = :postId")
    List<Long> countApplyByPost(@Param("postId") Long postId);

}
