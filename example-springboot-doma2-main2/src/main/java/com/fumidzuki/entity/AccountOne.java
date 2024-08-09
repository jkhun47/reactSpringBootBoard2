package com.fumidzuki.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
//@Table(schema = "example_one", name = "account_one")
@Table(schema = "test", name = "account_one")
public class AccountOne {

  @Id
  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "username")
  private String username;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
