package com.zack.labelcreation.repositories;

import com.zack.labelcreation.models.Label;
import com.zack.labelcreation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    List<Label> findByHost(User user);
    Label findByIdAndHost(Long id, User host);

}
