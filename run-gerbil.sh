#!/bin/bash

gerbil_data=gerbil_data

data_url="https://github.com/dice-group/gerbil/releases/download/v1.2.6/gerbil_data.zip"
mapping_url="https://hobbitdata.informatik.uni-leipzig.de/gerbil/dbpedia_index.zip"
index_url="https://hobbitdata.informatik.uni-leipzig.de/gerbil/dbpedia_check_index.zip"

zipped_data_file="/tmp/gerbil_data.zip"
zipped_mapping_file="/tmp/dbpedia_index.zip"
zipped_index_file="/tmp/dbpedia_check_index.zip"

mapping_file="$gerbil_data/indexes/dbpedia"
index_file="$gerbil_data/indexes/dbpedia_check"


echo " .. downloading mappings to $zipped_mapping_file"
curl --retry 4 -L -o "$zipped_mapping_file" "$mapping_url"
  
echo "extracting .."
unzip "$zipped_mapping_file" -d "$mapping_file"

echo " .. downloading indexes to $zipped_index_file"
curl --retry 4 -L -o "$zipped_index_file" "$index_url"
  
echo "extracting .."
unzip "$zipped_index_file" -d "$index_file"

echo "download complete"
