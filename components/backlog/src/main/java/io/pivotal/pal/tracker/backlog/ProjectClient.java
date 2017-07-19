package io.pivotal.pal.tracker.backlog;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.client.RestOperations;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ProjectClient {

    private final RestOperations restOperations;
    private final String registrationServerEndpoint;
    private ConcurrentMap<Long, ProjectInfo> cachedProjects = new ConcurrentHashMap<>();

    public ProjectClient(RestOperations restOperations, String registrationServerEndpoint) {
        this.restOperations= restOperations;
        this.registrationServerEndpoint = registrationServerEndpoint;
    }

    @HystrixCommand(fallbackMethod = "getProjectFromCache")
    public ProjectInfo getProject(long projectId) {
        ProjectInfo fetchedProjectInfo = restOperations
                .getForObject(
                        registrationServerEndpoint + "/projects/" + projectId,
                        ProjectInfo.class
                );

        cachedProjects.put(projectId, fetchedProjectInfo);
        return fetchedProjectInfo;
    }

    public ProjectInfo getProjectFromCache(long projectId) {
        return cachedProjects.get(projectId);
    }
}
