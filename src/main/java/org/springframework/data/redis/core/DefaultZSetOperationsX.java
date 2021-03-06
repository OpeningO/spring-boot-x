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

import org.openingo.spring.extension.data.redis.naming.IKeyNamingPolicy;
import org.springframework.data.redis.connection.RedisZSetCommands;

import java.util.Collection;
import java.util.Set;

/**
 * DefaultZSetOperationsX
 *
 * @author Qicz
 */
public class DefaultZSetOperationsX<V> extends DefaultZSetOperations<String, V> implements IKeyNamingPolicy {

    IKeyNamingPolicy keyNamingPolicy;

    public DefaultZSetOperationsX(RedisTemplate<String, V> template, IKeyNamingPolicy keyNamingPolicy) {
        super(template);
        this.keyNamingPolicy = keyNamingPolicy;
    }

    @Override
    public Boolean add(String key, V value, double score) {
        return super.add(this.getKeyName(key), value, score);
    }

    @Override
    public Long add(String key, Set<TypedTuple<V>> typedTuples) {
        return super.add(this.getKeyName(key), typedTuples);
    }

    @Override
    public Double incrementScore(String key, V value, double delta) {
        return super.incrementScore(this.getKeyName(key), value, delta);
    }

    @Override
    public Long intersectAndStore(String key, String otherKey, String destKey) {
        return super.intersectAndStore(this.getKeyName(key), this.getKeyName(otherKey), this.getKeyName(destKey));
    }

    @Override
    public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return super.intersectAndStore(this.getKeyName(key), this.getKeyNames(otherKeys), this.getKeyName(destKey));
    }

    @Override
    public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey, RedisZSetCommands.Aggregate aggregate, RedisZSetCommands.Weights weights) {
        return super.intersectAndStore(this.getKeyName(key), this.getKeyNames(otherKeys), this.getKeyName(destKey), aggregate, weights);
    }

    @Override
    public Set<V> range(String key, long start, long end) {
        return super.range(this.getKeyName(key), start, end);
    }

    @Override
    public Set<V> reverseRange(String key, long start, long end) {
        return super.reverseRange(this.getKeyName(key), start, end);
    }

    @Override
    public Set<TypedTuple<V>> rangeWithScores(String key, long start, long end) {
        return super.rangeWithScores(this.getKeyName(key), start, end);
    }

    @Override
    public Set<TypedTuple<V>> reverseRangeWithScores(String key, long start, long end) {
        return super.reverseRangeWithScores(this.getKeyName(key), start, end);
    }

    @Override
    public Set<V> rangeByLex(String key, RedisZSetCommands.Range range) {
        return super.rangeByLex(this.getKeyName(key), range);
    }

    @Override
    public Set<V> rangeByLex(String key, RedisZSetCommands.Range range, RedisZSetCommands.Limit limit) {
        return super.rangeByLex(this.getKeyName(key), range, limit);
    }

    @Override
    public Set<V> rangeByScore(String key, double min, double max) {
        return super.rangeByScore(this.getKeyName(key), min, max);
    }

    @Override
    public Set<V> rangeByScore(String key, double min, double max, long offset, long count) {
        return super.rangeByScore(this.getKeyName(key), min, max, offset, count);
    }

    @Override
    public Set<V> reverseRangeByScore(String key, double min, double max) {
        return super.reverseRangeByScore(this.getKeyName(key), min, max);
    }

    @Override
    public Set<V> reverseRangeByScore(String key, double min, double max, long offset, long count) {
        return super.reverseRangeByScore(this.getKeyName(key), min, max, offset, count);
    }

    @Override
    public Set<TypedTuple<V>> rangeByScoreWithScores(String key, double min, double max) {
        return super.rangeByScoreWithScores(this.getKeyName(key), min, max);
    }

    @Override
    public Set<TypedTuple<V>> rangeByScoreWithScores(String key, double min, double max, long offset, long count) {
        return super.rangeByScoreWithScores(this.getKeyName(key), min, max, offset, count);
    }

    @Override
    public Set<TypedTuple<V>> reverseRangeByScoreWithScores(String key, double min, double max) {
        return super.reverseRangeByScoreWithScores(this.getKeyName(key), min, max);
    }

    @Override
    public Set<TypedTuple<V>> reverseRangeByScoreWithScores(String key, double min, double max, long offset, long count) {
        return super.reverseRangeByScoreWithScores(this.getKeyName(key), min, max, offset, count);
    }

    @Override
    public Long rank(String key, Object o) {
        return super.rank(this.getKeyName(key), o);
    }

    @Override
    public Long reverseRank(String key, Object o) {
        return super.reverseRank(this.getKeyName(key), o);
    }

    @Override
    public Long remove(String key, Object... values) {
        return super.remove(this.getKeyName(key), values);
    }

    @Override
    public Long removeRange(String key, long start, long end) {
        return super.removeRange(this.getKeyName(key), start, end);
    }

    @Override
    public Long removeRangeByScore(String key, double min, double max) {
        return super.removeRangeByScore(this.getKeyName(key), min, max);
    }

    @Override
    public Double score(String key, Object o) {
        return super.score(this.getKeyName(key), o);
    }

    @Override
    public Long count(String key, double min, double max) {
        return super.count(this.getKeyName(key), min, max);
    }

    @Override
    public Long size(String key) {
        return super.size(this.getKeyName(key));
    }

    @Override
    public Long zCard(String key) {
        return super.zCard(this.getKeyName(key));
    }

    @Override
    public Long unionAndStore(String key, String otherKey, String destKey) {
        return super.unionAndStore(this.getKeyName(key), this.getKeyName(otherKey), this.getKeyName(destKey));
    }

    @Override
    public Long unionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return super.unionAndStore(this.getKeyName(key), this.getKeyNames(otherKeys), this.getKeyName(destKey));
    }

    @Override
    public Long unionAndStore(String key, Collection<String> otherKeys, String destKey, RedisZSetCommands.Aggregate aggregate, RedisZSetCommands.Weights weights) {
        return super.unionAndStore(this.getKeyName(key), this.getKeyNames(otherKeys), this.getKeyName(destKey), aggregate, weights);
    }

    @Override
    public Cursor<TypedTuple<V>> scan(String key, ScanOptions options) {
        return super.scan(this.getKeyName(key), options);
    }

    @Override
    public Set<byte[]> rangeByScore(String key, String min, String max) {
        return super.rangeByScore(this.getKeyName(key), min, max);
    }

    @Override
    public Set<byte[]> rangeByScore(String key, String min, String max, long offset, long count) {
        return super.rangeByScore(this.getKeyName(key), min, max, offset, count);
    }

    @Override
    public String getKeyName(String key) {
        return this.keyNamingPolicy.getKeyName(key);
    }
}
