package projects.service;

//imports
import projects.dao.ProjectDao;
import projects.entity.Project;

public class ProjectService {

	public Project addProject(Project project) {
		return ProjectDao.insertProject(project);
	}

}
