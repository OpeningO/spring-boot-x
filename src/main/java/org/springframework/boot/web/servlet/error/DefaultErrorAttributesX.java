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

package org.springframework.boot.web.servlet.error;

import lombok.extern.slf4j.Slf4j;
import org.openingo.jdkits.ObjectKit;
import org.openingo.jdkits.ThreadLocalKit;
import org.openingo.jdkits.ValidateKit;
import org.openingo.spring.constants.Constants;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * DefaultErrorAttributesX
 *
 * @author Qicz
 */
@Slf4j
public class DefaultErrorAttributesX extends DefaultErrorAttributes {

    private final ThreadLocalKit<Object> handlerHolder = new ThreadLocalKit<>();
    private final ThreadLocalKit<Exception> exceptionHolder = new ThreadLocalKit<>();
    private final ThreadLocalKit<Integer> statusHolder = new ThreadLocalKit<>();

    // using exception instance or not
    private final boolean usingException;

    // using response status or not
    private final boolean usingStatus;

    /**
     * Create a new {@link DefaultErrorAttributesX} instance that included the
     * "exception" attribute , can not get the "exception" instance and response status.
     */
    public DefaultErrorAttributesX() {
        this(false, false);
    }

    /**
     * Create a new {@link DefaultErrorAttributesX} instance.
     * default included the "exception" attribute.
     *
     * @param usingException whether to get the "exception" instance and response status.
     */
    public DefaultErrorAttributesX(boolean usingException, boolean usingStatus) {
        super(true);
        this.usingException = usingException;
        this.usingStatus = usingStatus;
    }

    /**
     * Returns a {@link Map} of the error attributes. The map can be used as the model of
     * an error page {@link ModelAndView}, or returned as a {@link ResponseBody}.
     * @param webRequest the source request
     * @param includeStackTrace if stack trace elements should be included
     * @return a map of error attributes
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Object handler = this.handlerHolder.get();
        if (ValidateKit.isNotNull(handler)) {
            errorAttributes.put("handler", handler.toString());
        }
        if (this.usingStatus) {
            this.statusHolder.set(ObjectKit.toInteger(errorAttributes.get("status")));
        }
        return errorAttributes;
    }

    /**
     * Try to resolve the given exception that got thrown during handler execution,
     * returning a {@link ModelAndView} that represents a specific error page if appropriate.
     * <p>The returned {@code ModelAndView} may be {@linkplain ModelAndView#isEmpty() empty}
     * to indicate that the exception has been resolved successfully but that no view
     * should be rendered, for instance by setting a status code.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  the executed handler, or {@code null} if none chosen at the
     *                 time of the exception (for example, if multipart resolution failed)
     * @param exception       the exception that got thrown during handler execution
     * @return a corresponding {@code ModelAndView} to forward to,
     * or {@code null} for default processing in the resolution chain
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        this.handlerHolder.set(handler);
        if (this.usingException) {
            this.exceptionHolder.set(exception);
        }
        StringBuilder errorBuilder = new StringBuilder();
        log.error(errorBuilder.append(Constants.REQUEST_REPORT_HEADER).toString());
        return super.resolveException(request, response, handler, exception);
    }

    /**
     * Returns current handler execution 's instance, may be <tt>null</tt>.
     *
     * if "usingException" is <tt>false</tt> will return <tt>null</tt>.
     *
     * @return current handler execution 's exception instance
     */
    protected Exception getHandlerExecutionException() {
        if (!this.usingException) {
            log.info("\"usingException\" state is Illegal, required true state.");
            return null;
        }
        return this.exceptionHolder.get();
    }

    /**
     * Current Request's status
     *
     * if "usingStatus" is <tt>false</tt> will return <tt>null</tt>.
     *
     * @see @{@linkplain org.springframework.http.HttpStatus}
     * @return response status
     */
    protected Integer getStatus() {
        if (!this.usingStatus) {
            log.info("\"usingStatus\" state is Illegal, required true state.");
            return null;
        }
        return this.statusHolder.get();
    }
}