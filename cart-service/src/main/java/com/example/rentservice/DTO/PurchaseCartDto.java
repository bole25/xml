package com.example.rentservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

//Informacije o tome za koje vlasnike hocemo da bundle-ujemo requestove, a za koje ne
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseCartDto {

    private Map<String, String> bundleQuery;
}
