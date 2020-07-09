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

package org.springframework.data.redis.core;

import com.sun.istack.internal.NotNull;
import org.openingo.spring.extension.data.redis.naming.IKeyNamingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * DefaultHyperLogLogOperationsX
 *
 * @author Qicz
 */
public class DefaultHyperLogLogOperationsX<V> extends DefaultHyperLogLogOperations<String, V> {

    IKeyNamingPolicy keyNamingPolicy;

    private String getKey(String key) {
        return this.keyNamingPolicy.getKeyName(key);
    }

    private String[] getKeys(String... keys) {
        return this.keyNamingPolicy.getKeyNames(keys);
    }

    public DefaultHyperLogLogOperationsX<V> setKeyNamingPolicy(IKeyNamingPolicy keyNamingPolicy) {
        this.keyNamingPolicy = keyNamingPolicy;
        return this;
    }

    public DefaultHyperLogLogOperationsX(RedisTemplate<String, V> template) {
        super(template);
    }

    @Override
    public Long size(String... keys) {
        return super.size(this.getKeys(keys));
    }

    @Override
    public Long union(String destination, String... sourceKeys) {
        return super.union(this.getKey(destination), this.getKeys(sourceKeys));
    }

    @Override
    public void delete(String key) {
        super.delete(this.getKey(key));
    }
}
