---
layout: post
title:  "Disqus를 이용하여 Jekyll에 댓글기능 추가하기"
date:   2017-06-01 09:51:42 +0900
categories: Jekyll disqus reply
tags:
- jekyll
- disqus
---

> **Jekyll**과 **Disqus**를 연동하면 자신의 프로젝트에 댓글 기능을 추가할 수 있습니다. 유료인 경우도 있으나 광고를 추가하거나 접속자가 많지 않은 경우 무료로도 이용이 가능합니다. <br>
 저는 무료 서비스 중 Non-Commercial(Under 5,000 total daily pageviews)로 선택하여 진행하였습니다.

#### 1. disqus 가입 (<https://disqus.com/>)
> ![1_disqus_site](/images/jekyll_disqus_reply/1_disqus_site.jpg)
![2_disqus_sign_up](/images/jekyll_disqus_reply/2_disqus_sign_up.jpg)

#### 2. I want to install Disqus my site 선택
> ![3_disqus_choice](/images/jekyll_disqus_reply/3_disqus_choice.jpg)

#### 3. new site 생성(저는 website name을 프로젝트 이름과 동일한 17billion으로 했습니다.)
> ![4_create_a_new_site](/images/jekyll_disqus_reply/4_create_a_new_site.jpg)

#### 4. Service Type 선택
> ##### 저는 무료 중 소규모 블로그에 적합한 타입을 선택해서 진행했습니다.
![5_choice_type](/images/jekyll_disqus_reply/5_choice_type.jpg)

#### 6. Platform(jekyll) 선택
> ![6_select_jekyll](/images/jekyll_disqus_reply/6_select_jekyll.jpg)

#### 7. 자신의 Jekyll 프로젝트에 Disqus 댓글 기능 추가
> ##### Disqus 댓글을 이용하기 위하여 사용자 Jekyll 프로젝트에 추가해야하는 내용들입니다. <br> 
3.2 버전 이전에 _layouts, _includes 및 _sass에 있던 초기 파일이 테마와 함께 패키지되었습니다. 그래서 전 github.com/jekyll/minima에 접속해서 _layouts, _includes 디렉토리 내 필요한 파일을 복사하여 사용했습니다.
##### 아래 1.의 내용은 default layout을 상속받아서 사용하는 layout(page)에 comment 변수를 추가하여 true 일 경우 댓글기능이 보이고 false인 경우 안보이게 하겠다는 의미입니다. 저는 _layouts/page.html에 내용을 추가해주었습니다.
```
---
layout: default
comments: true
---
```
![7_set_jekyll](/images/jekyll_disqus_reply/7_set_jekyll.jpg)

#### 7-2. 자신의 Jekyll 프로젝트에 Disqus 댓글 기능 추가 2
> ##### 위 7. 에서 Universal Embed Code를 클릭하시면 나타나는 내용입니다. 실제 댓글기능을 하는 부분을 생성해서 필요한 곳에 연동하라는 내용이고 댓글 갯수를 노출시키는 방법도 나타나 있습니다.
##### 저는 별도로 _includes/disqus_comments.html를 생성해서 post에서 상속받을 수 있도록 했습니다. (아래 링크된 _includes/disqus_comments.html 파일 참고, _config.yml 파일에 url: "https://{repository name}.github.io"이 추가되어 있어야함)
##### 추가적으로 아래는 댓글 카운트를 출력하여 보여주는 방법입니다. 저는 블로그 메인에 출력하고 싶어 _layouts/home.html에 내용을 추가해주었습니다. (아래 링크된 _layout/home.html 파일 참고)
##### 진행 후 configure 버튼을 누르시면 됩니다.
![7_2_set_jekyll](/images/jekyll_disqus_reply/7_2_set_jekyll.jpg)

#### 8. 블로그 이름 및 주소 입력 후 Complete 버튼 클릭
> ![8_complete set_jekyll](/images/jekyll_disqus_reply/8_complete set_jekyll.jpg)

#### 9. 위 과정이 모두 진행되었으면 아래와 같이 완료되었다는 창이 뜹니다.
> ![8_2_complete set_jekyll](/images/jekyll_disqus_reply/8_2_complete set_jekyll.jpg)

#### 10. 댓글 기능을 사용하기 위해서는 가입 시 입력한 Email에 접속하여 인증을 해야합니다. 아래 Verify... 버튼만 클릭하시면 됩니다.
> ![9_email](/images/jekyll_disqus_reply/9_email.jpg)

> 결론적으로 저는 댓글 기능을 on/off 할 수 있는 jekyll 프로젝트 내 _layouts/page.html과 실제 댓글기능을 하는 _includes/disqus_comments.html, 그리고 이 두 개를 상속받아서 포스팅할 때 사용할 _layouts/post.html 파일을 생성해서 진행하였습니다. 빠른 댓글 기능 추가를 원하시면 아래 파일들을 자신의 프로젝트로 적용해서 사용하시면 됩니다. <br>
- [_includes/disqus_comments.html](https://github.com/17billion/17billion.github.io/blob/master/images/jekyll_disqus_reply/disqus_comments.html) <br>
- [_layouts/page.html](https://github.com/17billion/17billion.github.io/blob/master/images/jekyll_disqus_reply/page.html) <br> 
- [_layouts/post.html](https://github.com/17billion/17billion.github.io/blob/master/images/jekyll_disqus_reply/post.html) <br>
- [_layouts/home.html](https://github.com/17billion/17billion.github.io/blob/master/images/jekyll_disqus_reply/home.html) <br>
  (home.html, disqus_comments.html 파일 내용 중 17billion 부분은 각자 생성하신 github repository name으로 진행하시면 됩니다.)



#### 아래는 적용 완료된 화면입니다.
> ![10_end](/images/jekyll_disqus_reply/10_end.jpg)
![10_end](/images/jekyll_disqus_reply/10_end2.jpg)

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
