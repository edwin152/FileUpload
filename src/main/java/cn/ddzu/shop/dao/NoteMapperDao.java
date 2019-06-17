package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Note;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteMapperDao extends Dao<Note> {

    @Override
    void drop();

    @Override
    void create();

    @Override
    void init();

    @Override
    List<Note> selectAll();

    @Override
    void insert(Note data);

    @Override
    void update(Note data);

    Note selectByKey(@Param("k") String k);
}
