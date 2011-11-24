/*
 * Copyright (c) 2010-2011 Jakub Białek
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.google.code.ssm.config;

import java.net.SocketAddress;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.google.code.ssm.providers.CacheClient;
import com.google.code.ssm.providers.CacheException;
import com.google.code.ssm.providers.CacheTranscoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Jakub Białek
 * @since 2.0.0
 * 
 */
public class MemcachedClientWrapper implements CacheClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemcachedClientWrapper.class);

    private volatile CacheClient memcachedClient;

    public MemcachedClientWrapper(CacheClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    @Override
    public boolean add(String key, int exp, Object value) throws TimeoutException, CacheException {
        return memcachedClient.add(key, exp, value);
    }

    @Override
    public <T> boolean add(String key, int exp, T value, CacheTranscoder<T> transcoder) throws TimeoutException, CacheException {
        return memcachedClient.add(key, exp, value, transcoder);
    }

    public void changeMemcachedClient(CacheClient newMemcachedClient) {
        if (newMemcachedClient != null) {
            LOGGER.info("Replacing the memcached client");
            CacheClient oldMemcachedClient = memcachedClient;
            memcachedClient = newMemcachedClient;
            LOGGER.info("Memcached client replaced");
            LOGGER.info("Closing old memcached client");
            oldMemcachedClient.shutdown();
            LOGGER.info("Old memcached client closed");
        }
    }

    @Override
    public long decr(String key, int by) throws TimeoutException, CacheException {
        return memcachedClient.decr(key, by);
    }

    @Override
    public long decr(String key, int by, long def) throws TimeoutException, CacheException {
        return memcachedClient.decr(key, by, def);
    }

    @Override
    public boolean delete(String key) throws TimeoutException, CacheException {
        return memcachedClient.delete(key);
    }

    @Override
    public void delete(Collection<String> keys) throws TimeoutException, CacheException {
        memcachedClient.delete(keys);
    }

    @Override
    public void flush() throws TimeoutException, CacheException {
        memcachedClient.flush();
    }

    @Override
    public Object get(String key) throws TimeoutException, CacheException {
        return memcachedClient.get(key);
    }

    @Override
    public <T> T get(String key, CacheTranscoder<T> transcoder) throws TimeoutException, CacheException {
        return memcachedClient.get(key, transcoder);
    }

    @Override
    public <T> T get(String key, CacheTranscoder<T> transcoder, long timeout) throws TimeoutException, CacheException {
        return memcachedClient.get(key, transcoder, timeout);
    }

    @Override
    public Collection<SocketAddress> getAvailableServers() {
        return memcachedClient.getAvailableServers();
    }

    @Override
    public Map<String, Object> getBulk(Collection<String> keys) throws TimeoutException, CacheException {
        return memcachedClient.getBulk(keys);
    }

    @Override
    public <T> Map<String, T> getBulk(Collection<String> keys, CacheTranscoder<T> transcoder) throws TimeoutException, CacheException {
        return memcachedClient.getBulk(keys, transcoder);
    }

    @Override
    public CacheTranscoder<?> getTranscoder() {
        return memcachedClient.getTranscoder();
    }

    @Override
    public long incr(String key, int by) throws TimeoutException, CacheException {
        return memcachedClient.incr(key, by);
    }

    @Override
    public long incr(String key, int by, long def) throws TimeoutException, CacheException {
        return memcachedClient.incr(key, by, def);
    }

    @Override
    public long incr(String key, int by, long def, int exp) throws TimeoutException, CacheException {
        return memcachedClient.incr(key, by, def, exp);
    }

    @Override
    public boolean set(String key, int exp, Object value) throws TimeoutException, CacheException {
        return memcachedClient.set(key, exp, value);
    }

    @Override
    public <T> boolean set(String key, int exp, T value, CacheTranscoder<T> transcoder) throws TimeoutException, CacheException {
        return memcachedClient.set(key, exp, value, transcoder);
    }

    @Override
    public void shutdown() {
        memcachedClient.shutdown();
    }

}