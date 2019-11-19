package com.jetpack.demos.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * @author wzc
 * @date 2019/1/15
 */
@Dao
public interface CompanyDao {
    @Query("SELECT * FROM company")
    List<Company> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Company company);
}
