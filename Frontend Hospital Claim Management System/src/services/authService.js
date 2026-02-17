import axiosInstance from "../api/axiosInstance";

export const loginUser = (data) => {
  return axiosInstance.post("/auth/login", data);
};

export const signupUser = (data) => {
  return axiosInstance.post("/auth/signup", data);
};
