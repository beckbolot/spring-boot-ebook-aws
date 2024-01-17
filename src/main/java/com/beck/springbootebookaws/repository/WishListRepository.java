package com.beck.springbootebookaws.repository;

import com.beck.springbootebookaws.db.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList,Long> {


}
