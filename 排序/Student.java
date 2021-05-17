package com.mj.排序;

public class Student implements Comparable<Student> {
	public int score;
	public int age;
	
	public Student(int score, int age) {
		this.score = score;
		this.age = age;
	}
	
	@Override
	public int compareTo(Student o) {
		return age - o.age;
	}

	@Override
	public String toString() {
		return "Student{" +
				"score=" + score +
				", age=" + age +
				'}';
	}
}
