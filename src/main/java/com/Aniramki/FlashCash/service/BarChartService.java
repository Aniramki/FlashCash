//package com.Aniramki.FlashCash.service;
//
//import com.Aniramki.FlashCash.repository.BarChartRepository;
//import com.Aniramki.FlashCash.repository.UserRepository;
//import com.Aniramki.FlashCash.service.dto.BarChartForm;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Map;
//
//@Service("BarChatService")
//
//public class BarChartService {
//    private final BarChartRepository barChartRepository;
//    private final UserRepository userRepository;
//
//    public BarChartService(UserRepository userRepository, BarChartRepository barChartRepository) {
//        this.userRepository = userRepository;
//        this.barChartRepository = barChartRepository;
//
//
//    }
//
//        public List<Map<String, Object>> getFriendAmountMonth(int userFromId, Integer yearTransfer) {
//        return barChartRepository.callFriendAmountMonth(userFromId, yearTransfer);
//    }
//
//
//    public UserAccount showChartBar(BarChartForm BarChartForm, String email) {
//        User user = userRepository.findUserByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        int BarChartId = user.getId();
//        userAccount.setIban(ibanForm.getIban());
//        return userAccountRepository.save(userAccount);
//    }
//
//
//
//    public Optional<User> passwordCompare(SignInForm form) {
//        Optional<User> optionalUser = userRepository.findUserByEmail(form.getEmail());
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            String hashedPassword = user.getPassword();
//
//            if (passwordEncoder.matches(form.getPassword(), hashedPassword)) {
//                return optionalUser; // Return the user if the password matches
//            } else {
//                throw new RuntimeException("Incorrect password.");
//            }
//        } else {
//            throw new RuntimeException("User with this email does not exist.");
//        }
//    }
//
//}
