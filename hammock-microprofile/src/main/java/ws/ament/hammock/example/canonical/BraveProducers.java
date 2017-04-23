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

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.http.SpanNameProvider;
import zipkin.Span;
import zipkin.reporter.Reporter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@ApplicationScoped
public class BraveProducers {
    @Produces
    @Singleton
    public Brave createBrave(Reporter<Span> reporter) {
        return new Brave.Builder().reporter(reporter).build();
    }

    @Produces
    @Singleton
    public SpanNameProvider createSpanNameProvider() {
        return h -> h.getUri().getPath();
    }
}
