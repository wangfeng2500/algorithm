package algorithm;

import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by IDEA
 * User：fenngwang
 * Date：2020/12/24
 * Time：19:00
 * Desc：
 【题目】宠物、狗和猫的类如下：
 【要求】实现一种狗猫队列的结构，要求如下：
        用户可以调用 add 方法将 cat 类或 dog 类的实例放入队列中；
        用户可以调用 pollAll 方法，将队列中所有的实例按照进队列的先后顺序依次弹出；
        用户可以调用 pollDog 方法，将队列中 dog 类的实例按照进队列的先后顺序依次弹出；
        用户可以调用 pollCat 方法，将队列中 cat 类的实例按照进队列的先后顺序依次弹出；
        用户可以调用 isEmpty 方法，检查队列中是否还有 dog 或 cat 的实例；
        用户可以调用 isDogEmpty 方法，检查队列中是否有 dog 类的实例；
        用户可以调用 isCatEmpty 方法，检查队列中是否有 cat 类的实例。
 */

public class CatDogQueue {

    public static class Pet {
        private String type;
        public Pet(String type) {
            this.type = type;
        }
        public String getPetType() {
            return this.type;
        }
    }

    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    public class PetEnter {
        private Pet pet;
        private long count;

        public PetEnter(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return this.pet;
        }
        public long getCount() {
            return this.count;
        }
        public String getEnterPetType() {
            return this.pet.getPetType();
        }
    }

    private Queue<PetEnter> dogQueue = new  ConcurrentLinkedQueue<>();

    private Queue<PetEnter> catQueue = new ConcurrentLinkedDeque<>();

    private int count = 0;  // 添加晚的count就大

    public int getCount() {return count;}

    public void Add(Pet pet)
    {
        if(pet.getPetType().equals("dog")) {
            dogQueue.add(new PetEnter(pet, this.count++));
        }
        else {
            catQueue.add(new PetEnter(pet, this.count++));
        }
    }

    public Pet pollAll(){
        if (!this.dogQueue.isEmpty() && !this.catQueue.isEmpty()) {
            if(this.dogQueue.peek().getCount() < this.catQueue.peek().getCount()) {
                return this.dogQueue.poll().getPet();
            } else {
                return this.catQueue.poll().getPet();
            }
        } else if (!this.dogQueue.isEmpty()) {
            return this.dogQueue.poll().getPet();
        } else if (!this.catQueue.isEmpty()) {
            return this.catQueue.poll().getPet();
        } else {
            throw new RuntimeException("err, queue is empty!");
        }
    }




    public static void main(String[] args) {

    }
}
