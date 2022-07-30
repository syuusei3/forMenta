package com.todo3.todo33.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 認証失敗時の処理
 * デフォルトだとリダイレクトしてしまうのでフロントでハンドリングしづらい
 * なのでレスポンスのステータスコードに401を設定するだけにしておく
 * フロントのaxisは200以外のステータスコードの時はcatchで処理できると思うのでそこで好きにハンドリング処理をすればいい
 */
public class FailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("------------------------ authenticate failure!! ------------------------------");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
