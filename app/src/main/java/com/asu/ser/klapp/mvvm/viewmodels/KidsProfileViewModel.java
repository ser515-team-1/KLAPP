package com.asu.ser.klapp.mvvm.viewmodels;

import android.util.Log;

import com.asu.ser.klapp.interfaces.KidsProfileCallBack;
import com.asu.ser.klapp.models.Student;
import com.asu.ser.klapp.mvvm.repositories.KidsProfileRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KidsProfileViewModel extends ViewModel implements KidsProfileCallBack {

    private MutableLiveData<List<Student>> kidsprofilelivedata;
    private KidsProfileRepository repository;

    public void init(){
        repository = KidsProfileRepository.getInstance();
        repository.init(this);
        kidsprofilelivedata = new MutableLiveData<>();
        kidsprofilelivedata.setValue(new ArrayList<Student>());
    }

    public LiveData<List<Student>> getAllKidsProfileLiveData(){
        return kidsprofilelivedata;
    }

    public void refreshList(){

        Log.d("updatelist", "refreshList: ");

        repository.getAllKidsProfileFromDataBase();
    }

    @Override
    public void kidsProfilesLoadedFromDB(List<Student> kidsProfiles) {

        Log.d("updatelist", "kidsProfilesLoadedFromDB: "+kidsProfiles.size());

        kidsprofilelivedata.postValue(kidsProfiles);
    }
}
