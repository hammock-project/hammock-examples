/*
 *  Copyright 2015 - 2017 John D. Ament
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.
 *
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package ws.ament.hammock.example.canonical;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zipkin.Span;
import zipkin.reporter.Reporter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoggingReporter implements Reporter<Span> {
    private final Logger logger = LogManager.getLogger(LoggingReporter.class);
    @Override
    public void report(Span span) {
        logger.info("Received request on span " + span.name + " request id "+span.idString());
    }
}
