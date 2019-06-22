---
layout: post
title:  "Java Object 파일에 저장하기 (serialize)"
date:   2018-03-05 12:21:12 +0900
categories: java
tags:
- java
---


### Java Object 파일에 저장하기 (serialize) <br>
- 객체를 파일에 저장하려면 직렬화(serialize)가 필요

```
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person implements Serializable {
	String name;
	int weight;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return this.weight;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// FILE WRITE
		Person p = new Person();
		p.setName("lee");
		p.setWeight(120);

		String path = "p.dat";

		ObjectOutputStream os = null;
		FileOutputStream fos = null;

		fos = new FileOutputStream(path);
		os = new ObjectOutputStream(fos);
		os.writeObject(p);

		// FILE READ
		ObjectInputStream ois = null;
		FileInputStream fis = null;

		fis = new FileInputStream("p.dat");
		ois = new ObjectInputStream(fis);

		Person p2 = (Person) ois.readObject();
		System.out.println(p2.getName());
		System.out.println(p2.getWeight());

		ois.close();

	}
}

```

※ 직렬화 시킨 class가 변경된 경우 이 전에 저장했던 파일에서 객체를 불러올 수가 없음, 저장할때와 불러올때의 serialize number가 다르기 때문.

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
