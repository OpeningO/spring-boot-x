/*
 * MIT License
 *
 * Copyright (c) 2020 OpeningO Co.,Ltd.
 *
 *    https://openingo.org
 *    contactus(at)openingo.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.openingo.spring.extension.data.redis;

import org.openingo.spring.extension.data.redis.naming.IKeyNamingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.lang.Nullable;

/**
 * RedisTemplateX< String, V>
 *
 * @author Qicz
 */
@SuppressWarnings("all")
public class RedisTemplateX<V> {

    @Autowired
    RedisTemplate<String, V> redisTemplate;

    @Autowired
    IKeyNamingPolicy keyNamingPolicy;

    // cache singleton objects (where possible)
    private @Nullable ValueOperations<String, V> valueOps;
    private @Nullable ListOperations<String, V> listOps;
    private @Nullable SetOperations<String, V> setOps;
    private @Nullable ZSetOperations<String, V> zSetOps;
    private @Nullable GeoOperations<String, V> geoOps;
    private @Nullable HyperLogLogOperations<String, V> hllOps;

    public ClusterOperations<String, V> opsForCluster() {
        return new DefaultClusterOperationsX<>(this.redisTemplate);
    }

    public GeoOperations<String, V> opsForGeo() {
        if (this.geoOps == null) {
            this.geoOps = new DefaultGeoOperationsX<>(this.redisTemplate).setKeyNamingPolicy(this.keyNamingPolicy);
        }
        return this.geoOps;
    }

    public BoundGeoOperations<String, V> boundGeoOps(String key) {
        return new DefaultBoundGeoOperationsX<>(key, this.redisTemplate).setKeyNamingPolicy(this.keyNamingPolicy);
    }

    public <HK, HV> BoundHashOperations<String, HK, HV> boundHashOps(String key) {
        DefaultBoundHashOperationsX<HK, HV> objectObjectDefaultBoundHashOperationsX = new DefaultBoundHashOperationsX<>(key, this.redisTemplate);
        return objectObjectDefaultBoundHashOperationsX.setKeyNamingPolicy(this.keyNamingPolicy);
    }

    public <HK, HV> HashOperations<String, HK, HV> opsForHash() {
        DefaultHashOperationsX<HK, HV> objectObjectDefaultHashOperationsX = new DefaultHashOperationsX<>(this.redisTemplate);
        return objectObjectDefaultHashOperationsX.setKeyNamingPolicy(this.keyNamingPolicy);
    }

    public HyperLogLogOperations<String, V> opsForHyperLogLog() {
        if (this.hllOps == null) {
            this.hllOps = new DefaultHyperLogLogOperationsX<>(this.redisTemplate).setKeyNamingPolicy(this.keyNamingPolicy);
        }
        return this.hllOps;
    }

    public ListOperations<String, V> opsForList() {
        if (this.listOps == null) {
            this.listOps = new DefaultListOperationsX<>(this.redisTemplate).setKeyNamingPolicy(this.keyNamingPolicy);
        }
        return this.listOps;
    }

    public BoundListOperations<String, V> boundListOps(String key) {
        return new DefaultBoundListOperationsX<>(key, this.redisTemplate).setKeyNamingPolicy(this.keyNamingPolicy);
    }

    public BoundSetOperations<String, V> boundSetOps(String key) {
        return new DefaultBoundSetOperationsX<>(key, this.redisTemplate).setKeyNamingPolicy(this.keyNamingPolicy);
    }

    public SetOperations<String, V> opsForSet() {
        if (this.setOps == null) {
            this.setOps = new DefaultSetOperationsX<>(this.redisTemplate).setKeyNamingPolicy(this.keyNamingPolicy);
        }
        return this.setOps;
    }

    public BoundValueOperations<String, V> boundValueOps(String key) {
        return new DefaultBoundValueOperationsX<>(key, this.redisTemplate).setKeyNamingPolicy(this.keyNamingPolicy);
    }

    public ValueOperations<String, V> opsForValue() {
        if (this.valueOps == null) {
            this.valueOps = new DefaultValueOperationsX<>(this.redisTemplate).setKeyNamingPolicy(this.keyNamingPolicy);
        }
        return this.valueOps;
    }

    public BoundZSetOperations<String, V> boundZSetOps(String key) {
        return new DefaultBoundZSetOperationsX<>(key, this.redisTemplate).setKeyNamingPolicy(this.keyNamingPolicy);
    }

    public ZSetOperations<String, V> opsForZSet() {
        if (this.zSetOps == null) {
            this.zSetOps = new DefaultZSetOperationsX<>(this.redisTemplate).setKeyNamingPolicy(this.keyNamingPolicy);
        }
        return this.zSetOps;
    }
}
