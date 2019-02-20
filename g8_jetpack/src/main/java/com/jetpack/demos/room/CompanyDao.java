package com.jetpack.demos.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

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
