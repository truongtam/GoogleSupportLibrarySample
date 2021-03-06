package com.sample.mysamples.model.db.model;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.sample.mysamples.R;
import com.sample.mysamples.model.app.AppController;
import com.sample.mysamples.model.db.DatabaseHelper;
import com.sample.mysamples.model.db.entity.User;

import java.util.List;

/**
 * Created by okadaakihito on 2015/06/24.
 */
public class UserModel {

    private static final String TAG = UserModel.class.getSimpleName();
    private Context mContext;
    private Resources mResources;

    public UserModel(Context context) {
        this.mContext = context;
        this.mResources = AppController.getInstance().getResources();
    }

    /**
     * insert or updateする
     *
     * @param User 対象のエンティティ
     */
    public void save(User User) {
        DatabaseHelper helper = new DatabaseHelper(mContext);
        try {
            Dao<User, Integer> dao = helper.getDao(User.class);
            dao.createOrUpdate(User);
        } catch (Exception e) {
            Log.e(TAG, mResources.getString(R.string.exception_message), e);
        } finally {
            helper.close();
        }
    }

    /**
     * deleteする
     *
     * @param User 対象のエンティティ
     * @return 削除件数
     */
    public int delete(User User) {
        DatabaseHelper helper = new DatabaseHelper(mContext);
        try {
            Dao<User, Integer> dao = helper.getDao(User.class);
            return dao.delete(User);
        } catch (Exception e) {
            Log.e(TAG, mResources.getString(R.string.exception_message), e);
        } finally {
            helper.close();
        }
        return 0;
    }

    /**
     * 全エンティティを取得する
     *
     * @return エンティティのリスト
     */
    public List<User> findAll() {
        DatabaseHelper helper = new DatabaseHelper(mContext);
        try {
            Dao<User, Integer> dao = helper.getDao(User.class);
            return dao.queryForAll();
        } catch (Exception e) {
            Log.e(TAG, mResources.getString(R.string.exception_message), e);
            return null;
        } finally {
            helper.close();
        }
    }

    /**
     * 全件を削除する
     *
     * @return 削除件数
     */
    public int deleteAll() {
        DatabaseHelper helper = new DatabaseHelper(mContext);
        try {
            Dao<User, Integer> dao = helper.getDao(User.class);
            return dao.delete(findAll());
        } catch (Exception e) {
            Log.e(TAG, mResources.getString(R.string.exception_message), e);
        } finally {
            helper.close();
        }
        return 0;
    }

    /**
     * ユーザを検索する
     *
     * @return エンティティのリスト
     */
    public List<User> findUser(String uid) {
        DatabaseHelper helper = new DatabaseHelper(mContext);
        try {
            Dao<User, Integer> dao = helper.getDao(User.class);
            QueryBuilder<User, Integer> queryBuilder = dao.queryBuilder();

            PreparedQuery<User> preparedQuery = queryBuilder.where().eq("userId", uid).prepare();

            List<User> list = dao.query(preparedQuery);

            return list;
        } catch (Exception e) {
            Log.e(TAG, mResources.getString(R.string.exception_message), e);
            return null;
        } finally {
            helper.close();
        }
    }

}
