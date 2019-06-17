package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Note;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteMapperDao extends Dao<Note> {

    Note selectByKey(@Param("k") String k);
}
