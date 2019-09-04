/*
 * Copyright 2019 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.thoughtworks.go.server.service.result;

import com.thoughtworks.go.serverhealth.HealthStateType;
import com.thoughtworks.go.serverhealth.ServerHealthService;
import com.thoughtworks.go.serverhealth.ServerHealthState;

/**
 * @understands updating server health information relating to disk space
 * @deprecated Use LocalizedOperationResult interface instead
 */
public class DiskSpaceOperationResult implements OperationResult {
    private final ServerHealthService serverHealthService;
    private boolean canContinue = true;

    public DiskSpaceOperationResult(ServerHealthService serverHealthService) {
        this.serverHealthService = serverHealthService;
    }

    @Override
    public ServerHealthState success(HealthStateType healthStateType) {
        ServerHealthState state = ServerHealthState.success(healthStateType);
        serverHealthService.update(state);
        return state;
    }

    @Override
    public ServerHealthState error(String message, String description, HealthStateType type) {
        ServerHealthState state = ServerHealthState.error(message, description, type);
        serverHealthService.update(state);
        canContinue = false;
        return state;
    }

    @Override
    public ServerHealthState warning(String message, String description, HealthStateType type) {
        ServerHealthState state = ServerHealthState.warning(message, description, type);
        serverHealthService.update(state);
        canContinue = false;
        return state;
    }

    @Override
    public ServerHealthState getServerHealthState() {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public boolean canContinue() {
        return canContinue;
    }

    public ServerHealthState unauthorized(String message, String description, HealthStateType id) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public ServerHealthState forbidden(String message, String description, HealthStateType id) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void conflict(String message, String description, HealthStateType healthStateType) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void notFound(String message, String description, HealthStateType healthStateType) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void accepted(String message, String description, HealthStateType healthStateType) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void ok(String message) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void notAcceptable(String message, final HealthStateType type) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void internalServerError(String message, HealthStateType type) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void insufficientStorage(String message, String description, HealthStateType type) {
        error(message, description, type);
    }

    @Override
    public void notAcceptable(String message, String description, final HealthStateType type) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void unprocessibleEntity(String message, String description, HealthStateType healthStateType) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void badRequest(String message, String description, HealthStateType healthStateType) {
        error(message, description, healthStateType);
    }

}
