package com.example.iu.myapplication.model.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.iu.myapplication.model.dao.History;
import com.example.iu.myapplication.model.dao.Work;

import com.example.iu.myapplication.model.dao.HistoryDao;
import com.example.iu.myapplication.model.dao.WorkDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig historyDaoConfig;
    private final DaoConfig workDaoConfig;

    private final HistoryDao historyDao;
    private final WorkDao workDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        historyDaoConfig = daoConfigMap.get(HistoryDao.class).clone();
        historyDaoConfig.initIdentityScope(type);

        workDaoConfig = daoConfigMap.get(WorkDao.class).clone();
        workDaoConfig.initIdentityScope(type);

        historyDao = new HistoryDao(historyDaoConfig, this);
        workDao = new WorkDao(workDaoConfig, this);

        registerDao(History.class, historyDao);
        registerDao(Work.class, workDao);
    }
    
    public void clear() {
        historyDaoConfig.clearIdentityScope();
        workDaoConfig.clearIdentityScope();
    }

    public HistoryDao getHistoryDao() {
        return historyDao;
    }

    public WorkDao getWorkDao() {
        return workDao;
    }

}
