package projects.service;

import java.util.List;
import java.util.NoSuchElementException;
//imports
import projects.dao.ProjectDao;
import projects.entity.Project;

public class ProjectService {

	//Method to call to dao and add a project to DB
	public Project addProject(Project project) {
		return ProjectDao.insertProject(project);
	}

	//Method to call to dao and fetch list of all projects
	public List<Project> fetchAllProjects() {
		return ProjectDao.fetchAllProjects();
		
	}

	//Method to call to dao and fetch projects by a user specified ID
	public Project fetchProjectById(Integer projectId) {
		return ProjectDao.fetchProjectId(projectId).orElseThrow(() -> new NoSuchElementException("Project with project ID: " + projectId + " does not exist."));
		
	}

}
