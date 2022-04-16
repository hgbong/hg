package com.example.hg.controller;

import com.example.hg.model.security.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

        // request에서 cookie를 조회
        // 정보 있으면 로그인 했다는 소리이고, request cookie에서 sessionId를 꺼냄
        // sessionId를 꺼내서, 서버 세션 저장소에서 http session 조회
        // session.get("user")로 User를 꺼내옴
        // 그 User에서 userName을 modelAttr에 세팅해준다.

        // 그럼 화면에서 세팅된다.

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getUserName());
        }

        return "index";
        // 제대로 구현한다고 치면 아래와 같지 않을까?

//        if (request.getCookies() == null) {
//            // 로그인되어 있지 않음
//        } else {
//            String sessionId = null;
//            for (Cookie cookie : request.getCookies()) {
//                // FIXME 지금은 임시로 user이지만, 나중에는 sessionId 와 같이 변경되어야 함
//                if ("JSESSIONID".equals(cookie.getName())) { // 톰캣 default session id 네임  cf. https://zara49.tistory.com/160
//                    sessionId = cookie.getValue();
//                    break;
//                }
//            }
//
//            if (sessionId == null) {
//                // 로그인되어 있지 않음
//            } else {
//                User user2 = (User) httpSession.getAttribute(sessionId);
//            }
//
//        }
//    }


    }
}
