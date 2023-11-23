package com.medn.projetprd.controllers;
import com.medn.projetprd.models.*;
import com.medn.projetprd.services.FolderService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import java.util.List;
import java.util.Stack;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/folders")
public class FolderController {
	@Autowired
    private FolderService folderService; // Ensure that you inject your repository correctly
	
	@GetMapping("/all")
    public List<Folder> getAllfolders() {
        return folderService.getAllFolder();
    }
	
	
	@GetMapping("/chemin/{id}")
	public String getFullChemin(@PathVariable Long id) {
	   return folderService.getFullChemin(id);
	}

@GetMapping("/parent/{id}")
public  List<Document> getFoldersAndFilesByParentId(@PathVariable Long id){
//        System.out.println(id);
        return folderService.getFoldersAndFilesByParentId(id);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Folder> getfolder(@PathVariable Long id) {
    	Folder folder = folderService.getFolderById(id);
        return new ResponseEntity<>(folder, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Folder> createfolder(@RequestBody Folder folder) {
    	if(folder.getParentFolderid()!=null && folderService.getFolderById(folder.getParentFolderid())!=null) {
    	   folderService.createfolder(folder);
    	}
        return new ResponseEntity<>(folder, HttpStatus.CREATED);
    }
    
    

    @PutMapping("/{folderId}")
    public ResponseEntity<Folder> updatefolder(@PathVariable Long folderId, @RequestBody Folder updatedfolder) {
        Folder folder = folderService.updateFolder(folderId, updatedfolder);
        return new ResponseEntity<>(folder, HttpStatus.OK);
    }

    @DeleteMapping("/{folderId}")
    public ResponseEntity<Void> deletefolder(@PathVariable Long folderId) {
        folderService.deleteFolder(folderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
