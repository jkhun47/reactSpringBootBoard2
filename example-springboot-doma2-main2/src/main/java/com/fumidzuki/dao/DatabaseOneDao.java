package com.fumidzuki.dao;

import java.util.List;

import com.fumidzuki.entity.BoardEntity;
import com.fumidzuki.dtos.BoardDto;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.Result;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fumidzuki.entity.AccountOne;

@Dao
@AnnotateWith(annotations = {@Annotation(target = AnnotationTarget.CLASS, type = Repository.class),
    @Annotation(target = AnnotationTarget.CONSTRUCTOR, type = Autowired.class),
    @Annotation(target = AnnotationTarget.CONSTRUCTOR_PARAMETER, type = Qualifier.class,
        elements = "\"databaseOneConfig\"")})
public interface DatabaseOneDao {

  @Select
  public List<AccountOne> selectAll();

  @Select
  public List<BoardEntity> selectAllLimit(SelectOptions options);

  @Select
  public List<BoardEntity> selectSearchLimit(SelectOptions options,String author,String title,String contents);

  @Select
  public List<BoardEntity> selectSearchBoard(String author,String title,String contents);

  @Select
  public List<BoardEntity> selectAllBoard();

  @Select
  public BoardEntity selectById(Long idx);

  @Insert
//  public Result<BoardEntity> addBoard(BoardEntity boardEntity);
  public Result<BoardEntity> insert(BoardEntity boardEntity);

//  @Update
//  public Result<BoardEntity> updateBoard(BoardEntity boardEntity);
//  }

  @Update
  public Result<BoardEntity> update(BoardEntity boardEntity);
  }
