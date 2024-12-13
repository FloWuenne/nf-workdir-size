/*
 * Copyright 2021, Seqera Labs
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

package nextflow.workdirsize

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import nextflow.Session
import nextflow.trace.TraceObserver
import nextflow.trace.TraceRecord
import nextflow.util.MemoryUnit
import nextflow.processor.TaskHandler
import java.nio.file.Files
import java.nio.file.Path
import nextflow.trace.TraceHelper

/**
 * Example workflow events observer
 *
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
@Slf4j
@CompileStatic
class WorkdirSizeObserver implements TraceObserver {

    @Override
    void onProcessComplete(TaskHandler handler, TraceRecord trace) {
        def taskHash = handler.task.hashLog
        def workDir = handler.task.workDir
        if (Files.exists(workDir)) {
            def size = Files.walk(workDir)
                .filter { Files.isRegularFile(it) }
                .mapToLong { Files.size(it) }
                .sum()
            def size_mu = size as MemoryUnit
            log.debug "$taskHash,$size,$size_mu"
        }
    }
}
