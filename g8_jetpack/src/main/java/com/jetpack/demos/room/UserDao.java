package com.jetpack.demos.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author wzc
 * @date 2019/1/7
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Query("SELECT * FROM user WHERE uid = :uid")
    User findByUid(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(User... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<User> users);

    @Update()
    int update(User user);
    @Update()
    int updateAll(User... users);

    @Update()
    int updateAll(List<User> users);

    @Delete
    int delete(User user);
    @Delete
    int deleteAll(List<User> users);
    @Delete
    int deleteAll(User ... users);
}
