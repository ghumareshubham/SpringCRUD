package com.gtasterix.demo.serviceImpl;

import com.gtasterix.demo.dto.UserDTO;
import com.gtasterix.demo.entity.User;
import com.gtasterix.demo.exceptions.BaseException;
import com.gtasterix.demo.interfaces.UserService;
import com.gtasterix.demo.repository.UserRepository;
import com.gtasterix.demo.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public User getByUserId(Integer userID) {
        return userRepository.findById(userID)
                .orElseThrow(()->new RuntimeException("User details not found by id"));

    }




    //GetAllUsers
    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();

    }






    //DeleteUser
    @Override
    public BaseResponseDTO deleteUserById(int id) {
        BaseResponseDTO response=new BaseResponseDTO();


            Optional<User>users=userRepository.findById(id);
            if(users.isPresent())
            {
            userRepository.deleteById(id);
                response.setMessage("User Deleted ....");
                response.setCode(String.valueOf(HttpStatus.OK.value()));
            }
            else {
                System.err.println("in else...");
//                throw new BaseException( );
                throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "User Not Found......");
            }


        return response;
    }


    //DeleteAllUsers
    @Override
    public BaseResponseDTO deleteAllUsers() {

        BaseResponseDTO response=new BaseResponseDTO();
        try{

            userRepository.deleteAll();
            response.setMessage("All Users Deleted ....");
            response.setCode(String.valueOf(HttpStatus.OK.value()));
        }catch(BaseException e)
        {

            response.setMessage("Error while deleting user....");
            response.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
        return response;
    }

    @Override
    public User updateUserDetailsByID(UserDTO userDTO,  int id) {

        Optional<User> user= userRepository.findById(id);


//        System.err.println("founded user in DB ..."+user.toString());

        if (userDTO.isEmpty()){
            if(user.isPresent())
            {
                user.get().setEmail(userDTO.getEmail());
                user.get().setPassword(userDTO.getPassword());
                user.get().setFirstName(userDTO.getFirstName());
                user.get().setLastName(userDTO.getLastName());
                user.get().setCity(userDTO.getCity());
                user.get().setState(userDTO.getState());
                user.get().setCountry(userDTO.getCountry());
                user.get().setPostalCode(userDTO.getPostalCode());
                user.get().setMobileNumber(userDTO.getMobileNumber());
                user.get().setGender(userDTO.getGender());

                return userRepository.save(user.get());

            }
            else{
                throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "User Not Found with this Id..");

            }

        }
        else {

           throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Required to fill all Fields..");

        }
//        return user.get();




    }

    @Override
    public User updateFieldsOfUserById(int id, Map<String, Optional> map) {


        User userFromDb  = userRepository.findById(id).get();

        for(Map.Entry<String ,Optional>  hm :map.entrySet()){

            String keyFromMap = hm.getKey();

            if(keyFromMap.equals("state")){

                Optional<Object> oa = hm.getValue();
                String value = oa.map (Object::toString).orElse (null);
                userFromDb.setState(value);

            }



//            if(keyFromMap.equals("name")){
//
//                Optional<Object> oa = hm.getValue();
//                String value = oa.map (Object::toString).orElse (null);
//                userFromDb.setName(value);
//
//            }
//
//            if(keyFromMap.equals("fatherName")){
//
//                Optional<Object> oa = hm.getValue();
//                String value = oa.map (Object::toString).orElse (null);
//                userFromDb.setFatherName(value);
//
//            }
//
//            if(keyFromMap.equals("address")){
//
//                Optional<Object> oa = hm.getValue();
//                String value = oa.map (Object::toString).orElse (null);
//                userFromDb.setAddress(value);
//
//            }
//
//            if(keyFromMap.equals("age")){
//
//                Optional<Integer> oa = hm.getValue();
//                Integer value = Integer.valueOf(oa.get());
//                userFromDb.setAge(value);
//
//            }
        }


        return userRepository.save(userFromDb);
    }


    //Create User
    @Override
    public BaseResponseDTO createUser(UserDTO userDTO) {
        BaseResponseDTO response=new BaseResponseDTO();

        validateAccount(userDTO);

        User user=new User();


        user.setGender(userDTO.getGender());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCity(userDTO.getCity());
        user.setState(userDTO.getState());
        user.setCountry(userDTO.getCountry());
        user.setPostalCode(userDTO.getPostalCode());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setGender(userDTO.getGender());

        Random random = new Random();
        Boolean flag = true;
        Integer randomReferenceNumber = 0;

        while(flag){
            randomReferenceNumber =  random.nextInt(89999999)+9999999;


            Optional<User> user1 = userRepository.findByReferenceId(randomReferenceNumber +"");
            if(user1.isEmpty())flag = false;

            System.err.println("inside whileloop");
            System.out.println(user1.toString());
        }

        String ReferenceIds = String.valueOf(randomReferenceNumber);
        System.out.println(ReferenceIds);
        user.setReferenceId(ReferenceIds);
        user.setCreatedDate(LocalDate.now());



        try{
            userRepository.save(user);
            response.setMessage("User saved Successfully....");
            response.setCode(String.valueOf(HttpStatus.OK.value()));
        }catch(BaseException e)
        {

            response.setMessage("Error while saving user....");
            response.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
        return response;
    }





    //Validate User
    private void validateAccount(UserDTO userDTO)  {
        // validate null data
        if (userDTO.isEmpty()) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Data must not be empty");
        }


        // validate duplicate username
        User user = userRepository.findByEmail(userDTO.getEmail());
        if (!ObjectUtils.isEmpty(user)) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Username already exists");
        }




    }
}
