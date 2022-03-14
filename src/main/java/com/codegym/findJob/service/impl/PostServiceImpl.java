package com.codegym.findJob.service.impl;

import com.codegym.findJob.model.Post;
import com.codegym.findJob.repository.IPostRepo;
import com.codegym.findJob.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    IPostRepo postRepo;

    @Override
    public List<Post> findAllPost() {
        return postRepo.findAll();
    }

    @Override
    public Post findPostById(long id) {
        return postRepo.findPostById(id);
    }

    @Override
    public List<Post> search(String title, String companyName, String address, Long idField, Double minSalary, Double maxSalary) {
        List<Post> byCompanyName = new ArrayList<>();
        List<Post> byAddress = new ArrayList<>();
        List<Post> byIdField = new ArrayList<>();
        List<Post> bySalary = new ArrayList<>();
        List<Post> listReturn = new ArrayList<>();
        List<Post> listCheck =  new ArrayList<>();
        if (companyName != null) {
            byCompanyName = postRepo.findLikeCompanyName(companyName);
            listCheck = byCompanyName;
        }
        if (address != null) {
            byAddress = postRepo.findLikeAddress(address);
            listCheck = byAddress;
        }
        if (idField != null) {
            byIdField = postRepo.findByField(idField);
            listCheck = byIdField;
        }
        if (maxSalary != null && minSalary != null) {
            bySalary = postRepo.findBySalary(minSalary, maxSalary);
            listCheck = bySalary;
        }else if (maxSalary == null && minSalary != null) {
            bySalary = postRepo.findByMinSalary(minSalary);
            listCheck = bySalary;
        }else if (maxSalary != null && minSalary == null) {
             bySalary = postRepo.findByMaxSalary(maxSalary);
             listCheck = bySalary;
        }
        if (listCheck.size() > 0){
            if (byCompanyName.size() > 0){
                for (int i = 0; i < byCompanyName.size(); i++) {
                    for (int j = 0; j < listCheck.size(); j++) {
                        if (byCompanyName.get(i).getId() == listCheck.get(i).getId()){
                            listReturn.add(byCompanyName.get(i));
                            break;
                        }
                    }
                }
            }
            if (byAddress.size() > 0){
                for (int i = 0; i < byAddress.size(); i++) {
                    for (int j = 0; j < listCheck.size(); j++) {
                        if (byAddress.get(i).getId() == listCheck.get(j).getId()){
                            listReturn.add(byAddress.get(i));
                            break;
                        }
                    }
                }
            }
            if (byIdField.size() > 0){
                for (int i = 0; i < byIdField.size(); i++) {
                    for (int j = 0; j < listCheck.size(); j++) {
                        if (byIdField.get(i).getId() == listCheck.get(j).getId()){
                            listReturn.add(byIdField.get(i));
                            break;
                        }
                    }
                }
            }
            if (bySalary.size() > 0){
                for (int i = 0; i < bySalary.size(); i++) {
                    for (int j = 0; j < listCheck.size(); j++) {
                        if (bySalary.get(i).getId() == listCheck.get(j).getId()){
                            listReturn.add(bySalary.get(i));
                            break;
                        }
                    }
                }
            }
            return listReturn;
        }else return null;
    }

}
