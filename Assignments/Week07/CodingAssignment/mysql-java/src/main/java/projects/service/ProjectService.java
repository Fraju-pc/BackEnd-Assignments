package projects.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

//imports
import projects.dao.ProjectDao;
import projects.entity.Category;
import projects.entity.Material;
import projects.entity.Project;
import projects.entity.Step;
import projects.exception.DbException;

public class ProjectService {

	// Method to call to dao and add a project to DB
	public Project addProject(Project project) {
		return ProjectDao.insertProject(project);
	}

	// Method to call to dao and fetch list of all projects
	public List<Project> fetchAllProjects() {
		// @formatter:off
		return ProjectDao.fetchAllProjects()
			.stream()
			.sorted((p1, p2) -> p1.getProjectId() - p2.getProjectId())
			.collect(Collectors.toList());
		// @formatter:on

	}

	// Method to call to dao and fetch projects by a user specified ID
	public Project fetchProjectById(Integer projectId) {
		return ProjectDao.fetchProjectId(projectId).orElseThrow(
				() -> new NoSuchElementException("Project with project ID: " + projectId + " doesn't exist."));

	}

	// Method to call to dao and modify the base project details
	public void modifyProjectDetails(Project project) {
		if (!ProjectDao.modifyProjectDetails(project)) {
			throw new DbException("Project with Id: " + project.getProjectId() + " doesn't exist");
		}

	}

	// Method to call to dao and delete the current project
	public void deleteProject(Integer projectId) {
		if (!ProjectDao.deleteProject(projectId)) {
			throw new DbException("Project with Id: " + projectId + " doesn't exist");
		}
	}

	// Method to call to dao and and add a material to the current project
	public void addMaterial(Material material) {
		ProjectDao.addMaterialToProject(material);

	}

	// Method to call to dao and add a step to the current project
	public void addStep(Step step) {
		ProjectDao.addStepToProject(step);

	}

	// Method to call to dao and fetch all available categories
	public List<Category> fetchCategories() {
		return ProjectDao.fetchAllCategories();
	}

	// Method to call to dao and add a category to the current project
	public void addCategoryToProject(Integer projectId, String category) {
		ProjectDao.addCategoryToProject(projectId, category);

	}

	// Method to call to dao and fetch the steps from the current project
	public List<Step> fetchSteps(Integer projectId) {
		return ProjectDao.fetchProjectSteps(projectId);
	}

	// Method to call to dao and update the step in the current project
	public void modifyStep(Step step) {
		if (!ProjectDao.modifyProjectStep(step)) {
			throw new DbException("Step with ID: " + step.getStepId() + " does not exist");
		}

	}

	// Method to call to dao and fetch the materials for the current project
	public List<Material> fetchMaterial(Integer projectId) {
		return ProjectDao.fetchProjectMaterials(projectId);
	}

	// Method to call to dao and modify the materials of the current project
	public void modifyMaterial(Material materials) {
		if (!ProjectDao.modifyProjectMaterials(materials)) {
			throw new DbException("Step with ID: " + materials.getMaterialId() + " does not exist");
		}

	}

}
