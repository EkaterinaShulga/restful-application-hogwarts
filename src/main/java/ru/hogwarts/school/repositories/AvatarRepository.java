package ru.hogwarts.school.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hogwarts.school.model.Avatar;


import java.util.Optional;


public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long> {

    Optional<Avatar> findAvatarByStudent_Id(Long studentId);




}
