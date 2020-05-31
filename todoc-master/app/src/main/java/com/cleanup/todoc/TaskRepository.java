package com.cleanup.todoc;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.base.TodocDatabase;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {

    private TaskDao mTaskDao;
    private LiveData<List<Task>> allTasks;

    public TaskRepository(Application application){
        TodocDatabase database = TodocDatabase.getInstance(application);
        mTaskDao = database.mTaskDao();
        allTasks = mTaskDao.getTasks();
    }

    public void insert(Task task){
        new InsertTaskAsynchTask(mTaskDao).execute(task);
    }
    public void update(Task task){
        new UpdateTaskAsynchTask(mTaskDao).execute(task);
    }
    public void delete(Task task){
        new DeleteTaskAsynchTask(mTaskDao).execute(task);
    }
    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    private static class InsertTaskAsynchTask extends AsyncTask<Task, Void, Void> {
        private TaskDao mTaskDao;

        public InsertTaskAsynchTask(TaskDao taskDao) {
            this.mTaskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mTaskDao.insertTask(tasks[0]);
            return null;
        }
    }

    private static class UpdateTaskAsynchTask extends AsyncTask<Task, Void, Void> {
        private TaskDao mTaskDao;

        public UpdateTaskAsynchTask(TaskDao taskDao) {
            this.mTaskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mTaskDao.updateTask(tasks[0]);
            return null;
        }
    }

    private static class DeleteTaskAsynchTask extends AsyncTask<Task, Void, Void> {
        private TaskDao mTaskDao;

        public DeleteTaskAsynchTask(TaskDao taskDao) {
            this.mTaskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mTaskDao.deleteTask(tasks[0]);
            return null;
        }
    }

    private static class GetAllTaskAsynchTask extends AsyncTask<Task, Void, Void> {
        private TaskDao mTaskDao;

        public GetAllTaskAsynchTask(TaskDao taskDao) {
            this.mTaskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mTaskDao.getTasks();
            return null;
        }
    }
}
