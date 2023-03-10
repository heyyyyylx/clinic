package com.example.sparrownotes.dao;

import android.content.Context;

import com.example.sparrownotes.bean.NoteBean;
import com.example.sparrownotes.util.DBHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * 笔记操作类
 *
 *
 */
public class NoteDao {
    private Dao<NoteBean, Integer> dao;

    public NoteDao(Context context) {
        try {
            this.dao = DBHelper.getInstance(context).getDao(NoteBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建数据
     *
     * @param data 数据
     */
    public void create(NoteBean data) {
        try {
            dao.createOrUpdate(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建数据集合
     *
     * @param datas 数据集合
     */
    public void createList(List<NoteBean> datas) {
        try {
            dao.create(datas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向note表中添加一条数据
     * create:插入一条数据或集合
     * createIfNotExists:如果不存在则插入
     * createOrUpdate:如果指定id则更新
     *
     * @param data
     */
    public void insert(NoteBean data) {
        try {
            dao.createIfNotExists(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过id删除指定数据
     *
     * @param id
     */
    public void delete(int id) {
        try {
            dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除表中的一条数据
     *
     * @param data
     */
    public void delete(NoteBean data) {
        try {
            dao.delete(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除表中数据集合
     *
     * @param datas
     */
    public void deleteList(List<NoteBean> datas) {
        try {
            dao.delete(datas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空数据
     */
    public void deleteAll() {
        try {
            dao.delete(dao.queryForAll());
        } catch (Exception e) {
        }
    }

    /**
     * 修改表中的一条数据
     *
     * @param data
     */
    public void update(NoteBean data) {
        try {
            dao.update(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询表中的所有数据
     *
     * @return 返回查询结果集
     */
    public List<NoteBean> queryAll(int userID) {
        List<NoteBean> notes = null;
        try {
            notes = dao.queryBuilder()
                    .orderBy("note_id", false)
                    .where().eq("user_id", userID)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    /**
     * 根据ID取出笔记信息
     *
     * @param id
     * @return
     */
    public NoteBean queryById(int id, int userID) {
        NoteBean note = null;
        try {
            note = dao.queryBuilder()
                    .where().eq("note_id", id)
                    .and().eq("user_id", userID)
                    .queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return note;
    }

    /**
     * 通过列名和数据查找
     *
     * @param columnName 列名
     * @param data       数据
     * @return
     */
    public List<NoteBean> queryForWhat(String columnName, String data, int userID) {
        List<NoteBean> notes = null;
        try {
            notes = dao.queryBuilder()
                    .orderBy("note_id", false)
                    .where().eq(columnName, data)
                    .and().eq("user_id", userID)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    /**
     * 模糊查询
     *
     * @param columnName
     * @param data
     * @return
     */
    public List<NoteBean> queryLikeWhat(String columnName, String data, int userID) {
        List<NoteBean> notes = null;
        try {
            notes = dao.queryBuilder()
                    .orderBy("note_id", false)
                    .where().like(columnName, "%" + data + "%")
                    .and().eq("user_id", userID)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }
}
