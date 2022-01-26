package com.example.hg.repository;

import com.example.hg.model.Board;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board, String> {
    List<Board> findByName(String name);

}
