package com.example.demo.Utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
// We are using this Response bcoz we have to standardize the structure we send to frontend
   private boolean success; // it holds true or false : true means success
   private String message; // here we are using this bcoz we want some time to send custom message
   private List<T> data; // if its post we can send our data here if not send null
}
