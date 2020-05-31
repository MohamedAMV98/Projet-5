package com.cleanup.todoc;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository mRepository;
    private LiveData<List<Task>> mTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mTasks = mRepository.getAllTasks();
    }

    public void insert(Task task){
        mRepository.insert(task);
    }

    public void delete(Task task){
        mRepository.delete(task);
    }

    public void update(Task task){
        mRepository.update(task);
    }

    public LiveData<List<Task>> getTasks(){
        return mTasks;
    }
}
