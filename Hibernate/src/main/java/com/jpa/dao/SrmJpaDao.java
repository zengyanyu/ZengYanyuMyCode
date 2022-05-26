package com.jpa.dao;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2021年7月2日 下午10:31:01
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface SrmJpaDao<T, ID extends Serializable> extends JpaDao<T, ID> {

}
