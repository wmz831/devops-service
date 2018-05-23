package io.choerodon.devops.infra.feign;

import javax.validation.Valid;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.choerodon.core.domain.Page;
import io.choerodon.devops.domain.application.valueobject.MemberRoleV;
import io.choerodon.devops.infra.dataobject.iam.OrganizationDO;
import io.choerodon.devops.infra.dataobject.iam.ProjectDO;
import io.choerodon.devops.infra.dataobject.iam.UserDO;

/**
 * Created by younger on 2018/3/29.
 */

@FeignClient(value = "iam-service")
public interface IamServiceClient {

    @GetMapping(value = "/v1/projects/{projectId}")
    ResponseEntity<ProjectDO> queryIamProject(@PathVariable("projectId") Long projectId);

    @GetMapping("/v1/organizations/self")
    ResponseEntity<OrganizationDO> queryOrganization();

    @GetMapping("/v1/organizations/{organizationId}")
    ResponseEntity<OrganizationDO> queryOrganizationById(@PathVariable("organizationId") Long organizationId);


    // TODO: PATH NEED TO BE CHANGE
    @RequestMapping(value = "/v1/project/{projectId}/memberRoles/single", method = RequestMethod.POST)
    ResponseEntity<MemberRoleV> addMemberRole(@PathVariable("projectId") Long projectId, @RequestBody @Valid MemberRoleV memberRoleVo);

    @GetMapping(value = "/v1/users")
    ResponseEntity<UserDO> queryByLoginName(@RequestParam("login_name") String loginName);

    @GetMapping(value = "/v1/organizations/{id}/projects")
    ResponseEntity<Page<ProjectDO>> queryProjectByOrgId(@PathVariable("id") Long id,@RequestParam("page") int page,@RequestParam("size") int size);
}
