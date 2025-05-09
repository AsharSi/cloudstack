// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package com.cloud.projects;

import java.util.List;

import com.cloud.user.Account;
import org.apache.cloudstack.framework.config.ConfigKey;

public interface ProjectManager extends ProjectService {
    public static final ConfigKey<Boolean> ProjectSmtpUseStartTLS = new ConfigKey<Boolean>(ConfigKey.CATEGORY_ADVANCED, Boolean.class, "project.smtp.useStartTLS", "false",
            "If set to true and if we enable security via project.smtp.useAuth, this will enable StartTLS to secure the connection.", true);

    public static final ConfigKey<String> ProjectSmtpEnabledSecurityProtocols = new ConfigKey<String>(ConfigKey.CATEGORY_ADVANCED, String.class, "project.smtp.enabledSecurityProtocols", "",
            "White-space separated security protocols; ex: \"TLSv1 TLSv1.1\". Supported protocols: SSLv2Hello, SSLv3, TLSv1, TLSv1.1 and TLSv1.2", true, ConfigKey.Kind.WhitespaceSeparatedListWithOptions, "SSLv2Hello,SSLv3,TLSv1,TLSv1.1,TLSv1.2");

    public static final ConfigKey<Boolean> ProjectSmtpUseAuth = new ConfigKey<>(ConfigKey.CATEGORY_ADVANCED, Boolean.class, "project.smtp.useAuth", "false",
            "If true, use SMTP authentication when sending emails", false, ConfigKey.Scope.ManagementServer);

    boolean canAccessProjectAccount(Account caller, long accountId);

    boolean canModifyProjectAccount(Account caller, long accountId);

    boolean deleteAccountFromProject(long projectId, Account account);

    List<Long> listPermittedProjectAccounts(long accountId);

    boolean projectInviteRequired();

    boolean allowUserToCreateProject();

    boolean deleteProject(Account caller, long callerUserId, ProjectVO project);

    long getInvitationTimeout();

    public static final String MESSAGE_CREATE_TUNGSTEN_PROJECT_EVENT = "Message.CreateTungstenProject.Event";
    public static final String MESSAGE_DELETE_TUNGSTEN_PROJECT_EVENT = "Message.DeleteTungstenProject.Event";

}
