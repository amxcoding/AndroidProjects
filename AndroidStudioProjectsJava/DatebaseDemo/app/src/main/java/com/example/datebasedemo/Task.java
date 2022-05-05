package com.example.datebasedemo;

import java.util.Objects;

public final class Task {
    private String name;
    private String desc;

    public Task(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return name.equals(task.name) &&
                desc.equals(task.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc);
    }
}
